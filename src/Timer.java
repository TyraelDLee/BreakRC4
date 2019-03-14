/**
 * Created by leety on 2018/10/21.
 */
public class Timer {
    public static String timer(long startTime, long endTime){
        float totalTime = (endTime-startTime);
        int sec = (int)totalTime/1000;
        totalTime = (totalTime-sec*1000);
        return "Total time use: "+sec+"."+(int)totalTime+"s\n";
    }

    public String timer_nostatic(long startTime, long endTime){
        float totalTime = (endTime-startTime);
        int sec = (int)totalTime/1000;
        totalTime = (totalTime-sec*1000);
        return "Total time use: "+sec+"."+(int)totalTime+"s\n";
    }
}
