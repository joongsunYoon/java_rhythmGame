package game_version3;
public class Player2 {

    // https://developer-talk.tistory.com/742 를 참조하여 HashMap containsValue를 사용하기 위한 override

    String name,id,password;

    public Player2(){}

    //테스트 삽입용
    public Player2(String name ,String id, String password){
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

    //표준적인 hashCode 구현
    @Override
    public int hashCode() {
        int prime = 31; //보통 사용되는 소수값
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }
    
    //최고부모클래스 Object활용
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Player2 other = (Player2) obj;
        return other.id.equals(id) && other.password.equals(password);
    }
}
