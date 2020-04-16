package org.pengfei.projects.hypertheasu;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ThesaurusSource {

    private URL sourceURL;
    private String storagePath;
    private String fileName;

    public ThesaurusSource(String url, String storagePath, String fileName) throws MalformedURLException {
        // build a URL object based on a string url
        sourceURL = new URL(url);
        this.storagePath=storagePath;
        this.fileName=fileName;
    }

    /**
     * This function establish a connexion to the given url and get content of that url and save it to a file
     *
     * @author Pengfei liu
     * @version 1.0
     * @since 2020-04-19
     * @return void
     **/

    public void getContent(){
        /** Step1. Create connexion and get a input steam*/
        BufferedReader br = null;
		try {
        // create a URL connexion
            URLConnection connexion = sourceURL.openConnection();

        // open the stream on the URL connexion and put it into BufferedReader
        br = new BufferedReader(
                new InputStreamReader(connexion.getInputStream()));

            System.out.println("Connexion established");
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

		/** Step2. Create a file and write the input stream of the connexion to the file*/
        String inputLine;

        //save content to this location, only works for linux, for windows, need to change
        String fileAbs = storagePath+"/"+fileName;

        // use try with resource, so no need to close FileWriter. We also use BufferedWriter to encapsulate FileWriter.
        // This can avoid i/o blocking which can improve performance. We set append false, each execution will rewrite
        // the file with new content.
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileAbs,false))){

            while (br!=null&&(inputLine = br.readLine()) != null) {
                bw.write(inputLine);
                //System.out.println(inputLine);
            }
            // Url connexion does not implement autoclosable interface, so can't use try with resource. Need to close
            // it explicitly.
            br.close();

            System.out.println("Content saved to: "+fileAbs);
        }  catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
