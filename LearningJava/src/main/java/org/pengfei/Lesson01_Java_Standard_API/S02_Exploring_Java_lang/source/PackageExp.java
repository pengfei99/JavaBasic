package org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source;

public class PackageExp {

    public static void exp1(){
        Package pkgs[]=Package.getPackages();
        for(Package p: pkgs){
            System.out.println(
                            "Package name: "+p.getName()+
                            " Implementation title: "+ p.getImplementationTitle()+
                                    " Implementation Vendor: "+ p.getImplementationVendor()+
                                    " Implementation Version: "+ p.getImplementationVersion()+
                                    " Specification title: "+ p.getSpecificationTitle()+
                                    " Specification Vendor: "+ p.getSpecificationVendor()+
                                    " Specification Version: "+ p.getSpecificationVersion()

            );
        }
    }
}
