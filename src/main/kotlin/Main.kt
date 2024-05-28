
import io.javalin.Javalin;
import java.io.FileNotFoundException
import java.io.File
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import kotlin.error;
import kotlin.io.readLine
class MainLogger(){
    val logger = LoggerFactory.getLogger(this.javaClass);
}

fun main() {
    val log = MainLogger()
    var messages: String = ""
    println("Hello World!")
    val fileLoader = FileLoader()
    val app = Javalin.create(/*config*/)
        .get("/") {ctx -> 
            try{
                val htmlContent:String = fileLoader.loadFile("public/index.html")
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



private fun loadResource(file: String) = String::class.java.getResource("/public/index.html")?.readText() ?: "<handle default. or handle custom exception>"

