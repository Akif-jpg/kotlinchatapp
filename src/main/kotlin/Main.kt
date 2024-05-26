
import io.javalin.Javalin;
import java.io.FileNotFoundException
import java.io.File
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import kotlin.error;
class MainLogger(){
    val logger = LoggerFactory.getLogger(this.javaClass);
}

fun main() {
    val log = MainLogger()
    var messages: String = ""
    println("Hello World!")
    val app = Javalin.create(/*config*/)
        .get("/") {ctx -> 
            try{
                val htmlContent:String = File("src/main/resources/public/index.html").inputStream().readBytes().toString(Charsets.UTF_8)
                ctx.html(htmlContent);
            }catch(e : FileNotFoundException){
                log.logger.error(e.toString());
            }
         }

        app.ws("/chat"){
            ws ->
            ws.onConnect{
                ctx ->
                ctx.send("bir kullanici baglandi")
            }
            ws.onMessage{
                ctx->
                val message: String = ctx.message()
                if(message != "connect")
                    messages += "<br> " + message
                ctx.send(messages)
            }
        }
        app.start(7070)
}