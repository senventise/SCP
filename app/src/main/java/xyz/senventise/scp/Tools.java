package xyz.senventise.scp;

public class Tools {
    // 将序号补全 eg. 23 --> 023
    public static String idConvert(int id){
        String name = id+"";
        if(name.length()==1){
            return  "00" + name;
        }else if(name.length()==2){
            return  "0" + name;
        }else{
            return name;
        }
    }
}
