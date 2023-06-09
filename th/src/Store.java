
import java.util.*;
public class Store {
    double TotalNum;
    String request;
    Date data;
    ArrayList<String> menu;
    Store(){
        TotalNum = 0;
        request = null;
        data = null;
        menu = new ArrayList<>();
    }

    void SetTotalNum(MenuContext menuContext){
        this.TotalNum=menuContext.getOrderNumber();
    }
    void Setequest(String request){
        this.request = request;

    }
    void Setdata(Date data){
        this.data = data;

    }
    void Setmenu(Item its){
        menu.add(its.name);
    }



}

