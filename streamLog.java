import java.io.*;
import java.util.regex.*;
class streamLog{
    public static void main(String args[]){
        File fileR;
        try{
            if (args.length == 1) {
                fileR = new File(args[0]);
            } else {
                fileR = new File("./Map.txt");
            }
            if (checkBeforeReadfile(fileR)){
                BufferedReader br = new BufferedReader(new FileReader(fileR));
                String outputFile = "./out/"+fileR.getName();
                File fileW = new File(outputFile);
                FileOutputStream fos = new FileOutputStream(fileW);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                PrintWriter pw = new PrintWriter(osw);
                String str;
                String distance = "0;";
                while((str = br.readLine()) != null){
                    String regex = ";";
                    Pattern p = Pattern.compile(regex);
                    Matcher m = p.matcher(str);
                    int matchNum = 0;

                    while (m.find()) matchNum++;

                    boolean convert = false;
                    int index = str.indexOf(regex);
                    if (matchNum > 0 && index > 0) {
                        try{
                            int dis = Integer.parseInt(str.substring(1, index));
                            convert = true;
                        } catch (Exception e){
                            
                        }
                    }
                    if (convert) distance = str.substring(0, index+1);
                    if (!str.contains(distance)){
                        pw.println(distance+"+$offset;"+str);
                    } else {
                        pw.println(str);
                    }
                }

                br.close();
                pw.close();
                System.out.println("finished");
            } else {
                System.out.println("ファイルが見つからないか開けません");
            }
        } catch (FileNotFoundException e) {
                System.out.println(e);
        } catch(IOException e) {
                System.out.println(e);
        }
    }
    private static boolean checkBeforeReadfile(File file){
        if (file.exists()){
            if (file.isFile() && file.canRead()){
                return true;
            }
        }
        return false;
    }
}