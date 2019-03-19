package com.truebeans.easyinternetcall;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ExampleCaller extends InternetCaller {

    public ExampleCaller(){

        //initialize local variables
        //call execute where you need
        execute();

    }

    @Override
    public RequestBody getRequestBody() {
        //create and return the okhttp3 RequestBody
        return new FormBody.Builder()
                .add("myParameter1", "value1")
                .add("myParameter2", "value2")
                .build();
    }

    @Override
    public String getPageUrl() {
        //return the page url ( page.php )
        return "myPage.php";
    }

    @Override
    public String getHostingUrl() {
        //return the hosting url ( http://hosting.com )
        return "http://myWebsite.com/";
    }

    @Override
    public void responseReceived(String response) {
        //use server response here
    }


}
