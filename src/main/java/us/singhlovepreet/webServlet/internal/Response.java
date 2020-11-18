package us.singhlovepreet.webServlet.internal;

import lombok.Getter;
import lombok.Setter;

import java.io.OutputStream;
import java.io.PrintWriter;


@Getter
@Setter
public class Response {

    private OutputStream outputStream;

    private PrintWriter printWriter;

    public Response(OutputStream out){
        this.outputStream = out;
        this.printWriter = new PrintWriter(out,true);
    }

}
