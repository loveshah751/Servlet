package us.singhlovepreet.webServlet.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import us.singhlovepreet.socket.ServerContainer;
import us.singhlovepreet.utils.WebConstants;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Log
public class Request {

    private String protocol;

    private MethodType requestType;

    private String hostName;

    private String path;

    private Map<String, String> headers;

    private Map<String, String> queryParams;

    private Map<String, String> body;

    @AllArgsConstructor
    public enum MethodType {
        GET("get"), POST("post"), DELETE("delete");

        private String code;

        private static MethodType get(String value) {
            return StringUtils.isNotBlank(value) ? MethodType.valueOf(value)
                    : MethodType.GET;
        }

        private boolean isPost() {
            return this == POST;
        }

        private boolean isGET() {
            return this == GET;
        }
    }

    /**
     * This Method will parse the input Request sent by the browser into request object.
     * Step1.) Parse the Request Method Type, Protocol and url Path.
     * Step2.) Identify whether it is GET or POST request.
     * Step3.) Parse the hostname. For Instance : Localhost:8081
     * Step4.) Parse all the request headers.
     * Step5.) Parse the queryParams or request body.
     *
     * @param server
     * @return <code>true</code> if the request is successfully parsed or else <code> false</code>
     */
    public boolean parseRequest(ServerContainer server) throws IOException {

        String webRequest = server.getMessageFromBufferReader();
        var webRequestArr = webRequest.split(WebConstants.EMPTY_SPACE);

        if (webRequestArr.length != 3) return false;

        this.requestType = MethodType.get(webRequestArr[0]);
        var requestUrl = webRequestArr[1];
        this.protocol = webRequestArr[2];

        if (this.requestType.isGET()) {
            parseGETRequest(requestUrl);
            parseHostName(server);
            parseRequestHeader(server);
        } else if (this.requestType.isPost()) {
            this.setPath(requestUrl);
            parseHostName(server);
            parseRequestHeader(server);
            parsePOSTRequest(server);
        }

        return Objects.nonNull(this.getHostName()) && Objects.nonNull(this.getRequestType());
    }

    private void parseRequestHeader(ServerContainer server) {
        String webRequest = server.getMessageFromBufferReader();
        while (IsWebRequestNotEmpty(webRequest)) {
            parseRequestHeaders(webRequest);
            webRequest = server.getMessageFromBufferReader();
        }
    }

    private void parseHostName(ServerContainer server) {
        String[] webRequestArr;
        String webRequest;
        webRequest = server.getMessageFromBufferReader();
        //Map hostName
        if (IsWebRequestNotEmpty(webRequest)) {
            webRequestArr = webRequest.split(WebConstants.EMPTY_SPACE);
            this.hostName = webRequestArr[1];
        }
    }

    private void parseGETRequest(String requestUrl) {
        var queryStringIndex = StringUtils.indexOf(requestUrl, WebConstants.QUESTION_MARK);
        if (queryStringIndex > -1) {
            this.path = requestUrl.substring(0, queryStringIndex);
            parseRequestParam(StringUtils.substring(requestUrl, queryStringIndex + 1));
            log.info(WebConstants.QUERY_PARAM_LOG_MSG +this.queryParams.toString());
        } else {
            this.path = requestUrl;
        }
    }

    private void parsePOSTRequest(ServerContainer server) throws IOException {
        this.body = new HashMap<>();

        var reader = server.getBufferReader().get();
        StringBuilder body = new StringBuilder();
        while (reader.ready()) {
            body.append((char) reader.read());
        }

        String data = body.toString();
        log.info(WebConstants.REQUEST_BODY_LOG_MSG + data);
        String[] requestObjectArr = preProcessRequestBodyParsing(data);

        /**
         * "userId": "",
         *                 "OrderId":"89769422N",
         *                 "validStartDate":"31-10-2020",
         *                 "validEndDate":"13-11-2020"
         */
        for (var value : requestObjectArr) {
            var keyValuePair = value.split(WebConstants.COLON_CHAR);
            this.body.put(StringUtils.strip(keyValuePair[0]), StringUtils.strip(keyValuePair[1]));
        }
    }


    private String[] preProcessRequestBodyParsing(String data) {
        data = data.strip().replace(WebConstants.JSON_START_TAG, WebConstants.EMPTY_SPACE);
        data = data.replace(WebConstants.JSON_END_TAG, WebConstants.EMPTY_SPACE);
        return StringUtils.strip(data).split(WebConstants.COMA);
    }

    /**
     * name="Sam"&Age="23" {[name="sam"],[Age="23"]} {[[name],[sam]],[[Age],[same]]}
     *
     * @param queryParams
     */
    private void parseRequestParam(String queryParams) {
        this.queryParams = Arrays.stream(queryParams.split(WebConstants.AND_CHAR)).map(param -> param.split(WebConstants.EQUAL_CHAR))
                .collect(Collectors.toMap(arr -> arr[0], arr1 -> arr1[1],
                        (oldValue, newValue) -> newValue, HashMap::new));

    }

    private void parseRequestHeaders(String headers) {
        var arr = headers.split(WebConstants.COLON_CHAR);
        var key = arr[0];
        var value = arr[1];
        if (isMapEmpty(this.headers)) {
            this.headers = new HashMap<>();
        }
        this.headers.put(key, value);
    }

    private boolean IsWebRequestNotEmpty(String webRequest) {
        return StringUtils.isNotBlank(webRequest);
    }

    private boolean isMapEmpty(Map map) {
        return map == null || map.isEmpty();
    }
}

