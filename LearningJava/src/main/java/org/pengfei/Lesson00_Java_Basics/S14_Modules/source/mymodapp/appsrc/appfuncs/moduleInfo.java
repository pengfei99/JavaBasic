
// In Intellij, one project can only have one module-info.java. To store the source here, we
// have to change the name to moduleInfo. Note this file is the module description file for
// module simplefuncs

/*
* The compile raise many error:
* 1. keyword module must located in a file named module-info.java
* 2. module-info.java file must in the module source root folder
* */

 /*module appfuncs{
//exports the package appfuncs.simplefuncs
    exports appfuncs.simplefuncs;
}
*/