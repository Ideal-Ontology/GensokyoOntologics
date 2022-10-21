package github.thelawf.gensokyoontology.common.libs;

import net.minecraft.network.datasync.DataParameter;
import org.lwjgl.system.CallbackI;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class IdealismLib {

    public LocalDate localDate = LocalDate.now();
    public int date;
    public int year;
    public boolean isContinueOffline = false;

    // 写不来依赖库，只能依靠传统艺能：写 txt文档
    public void saveTimeData() {
        this.date = Integer.parseInt(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(localDate).substring(0,2));
        this.year = Integer.parseInt(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(localDate).substring(6,10));
        try {
            InputStream stream = Objects.requireNonNull(
                    this.getClass().getClassLoader().getResourceAsStream("src/ExampleCode.txt"));
            BufferedInputStream bis = new BufferedInputStream(stream);
            BufferedReader txt = new BufferedReader(new InputStreamReader(bis));
            //DataParameter
            /*
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File()));
            txt.write(date + "," + year + "\n");
            txt.close();
            */

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int[][] loadTimeData() {
        int[][] result = new int[2][2];
        try {
            BufferedReader reader = new BufferedReader(new FileReader("time_format.txt"));
            if (Objects.isNull(reader.readLine())) {
                return new int[2][2];
            }
            List<String> allLines = Arrays.asList(reader.readLine().split("\n"));
            String prevLine = allLines.get(allLines.size()-2);
            String nextLine = allLines.get(allLines.size()-1);

            int prevDateIn = Integer.parseInt(prevLine.split(",")[0]);
            int prevYearIn = Integer.parseInt(prevLine.split(",")[1]);

            int recentDateIn = Integer.parseInt(nextLine.split(",")[0]);
            int recentYearIn = Integer.parseInt(nextLine.split(",")[1]);

            result[0][0] = prevDateIn;
            result[0][1] = prevYearIn;
            result[1][0] = recentDateIn;
            result[1][1] = recentYearIn;

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
