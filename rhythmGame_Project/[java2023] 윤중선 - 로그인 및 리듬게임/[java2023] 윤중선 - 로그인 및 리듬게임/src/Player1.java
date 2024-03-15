public class Player1 {

    String name,id,password;

    public Player1(){}
    public Player1(String name ,String id, String password){
        this.name = name;
        this.id = id;
        this.password = password;
    }

    public String getId(){
        return id;
    }
    
    public String getPassword(){
        return password;
    }

    public String getName(){
        return name;
    }
}
