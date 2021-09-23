package lib;

//使用了OkHttp4.9.0
//使用方式见下方两个简单的Test
//Author:TWENTYY
//Website:www.twentyy.cn
//E-mail:twentyershi@hotmail.com


import okhttp3.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Download2File {

    //测试1
    void testDownload1() {
        System.out.println("test");
        String res = new String();
        res = Download2String(" http://不可描述的URL链接");
        System.out.println(res);
    }
    //测试2
    void test2()
    {
        //下载文件并使用二进制读写保存
        System.out.println("test Write byte[] File");
        String url = "http://不可描述的URL链接";
        InputStream is = Download2InputStream(url);
        if (WriteFile4InputStream("d:\\234.ts",is))
        {
            System.out.println("下载成功");

        }else{
            System.out.println("下载失败");
        }


    }


    //传入url链接，获取Text类型的文件（适合文本文件
    public String Download2String(String url)
    {
        String result = null;
        //okhttp3 获取Http请求
        try {
            result = getResponeBody(url).string();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        //返回获得值
        return  result;
    }

    //通过url获取InputStream
    public InputStream Download2InputStream(String url)
    {
        InputStream result = null;
        result = getResponeBody(url).byteStream();
        return result;
    }
    //通过url获取ResponseBody，用于转换成String等类型
    public ResponseBody getResponeBody(String url)
    {
        ResponseBody result =null;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
//            System.out.println(response.headers());
//            System.out.println(response.body().string());
            result = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    //将InputStream写入到文件，成功返回true 失败返回false
    public boolean WriteFile4InputStream(String FilePath,InputStream inputStream)
    {
        //默认为flase 即失败
        boolean result = false;
        try {
            OutputStream os = new FileOutputStream(FilePath);
            os.write(inputStream.readAllBytes());
            os.close();
            result = true;
        }catch (IOException e)
        {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
