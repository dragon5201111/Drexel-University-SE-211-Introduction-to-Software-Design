public class BaseMessage implements Message{
   private final String message;

   public BaseMessage(String message){
       this.message = message;
   }

    @Override
    public String getContent() {
        return this.message;
    }
}
