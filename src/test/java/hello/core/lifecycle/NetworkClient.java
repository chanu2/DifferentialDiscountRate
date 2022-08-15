package hello.core.lifecycle;


import javax.annotation.PostConstruct; //javax 스프링이 아닌 다른 컨테이너에서도 동작된다
import javax.annotation.PreDestroy;

public class NetworkClient { //인터페이스를 통한 콜백 잘 사용하지 않는다

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " +url);
        //connect();
        //call("초기화 연결 메시지");

    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect: "+ url);
    }

    public void call(String message){
        System.out.println("call: "+url + " message : " + message);

    }

    // 서비스 종료시 호출
    public void disconnect(){
        System.out.println("close: " + url);

    }

    @PostConstruct  //
    public void init() { //의존 관계 주입이 끝나면
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");

    }

    @PreDestroy
    public void close() { // bea 종료될 때
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
