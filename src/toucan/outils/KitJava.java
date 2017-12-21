package toucan.outils;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

import toucan.modele.algos.Algo;
import toucan.modele.cases.LesCases;

public class KitJava {
    private static KitJava instance = new KitJava();
    protected String laClasse;
    protected static String nomClasse = "AlgoPerso";
    protected static String nomPackage = "toucan.modele.algos";

    protected JavaCompiler compiler;
    protected ClassFileManager fileManager;

    /**
     * Constructeur de la class KitJava
     */
    private KitJava() {
        compiler = ToolProvider.getSystemJavaCompiler();
        fileManager = new ClassFileManager(compiler.getStandardFileManager(null, null, null));
    }

    /**
     * Fonction de construction de la class AlgoPerso
     *
     * @param code code rentre par l utilisateur
     */
    public void construireClasse(String code) {
        StringBuilder strb = new StringBuilder();
        strb.append("package toucan.modele.algos ;");
        strb.append("import toucan.modele.cases.LesCases; \n");
        strb.append("public class AlgoPerso extends Algo {\n");
        strb.append("private int[] tab;\n");
        strb.append("private int nbCases;\n");
        strb.append("public AlgoPerso(LesCases lesCases, int[] tab) {\n");
        strb.append("super(lesCases, tab);\n");
        strb.append("nbCases = lesCases.getNbCases() - 1;\n");
        strb.append("this.tab = tab;\n");
        strb.append("}\n\n");
        strb.append("@Override \n");
        strb.append("public void trier() {\n");
        strb.append(code + "\n");
        strb.append("}\n}");
        laClasse = strb.toString();
    }

    private Iterable<JavaSource> getJavaSourceFromString(String fileName, String code) {
        return Collections.singletonList(new JavaSource(fileName, code));
    }

    /**
     * Fonction qui compile le code genere
     *
     * @throws Exception
     */
    public void compiler() throws Exception {
        // writer pour écrire les erreurs de compilation
        StringWriter sortieErreur = new StringWriter();

        Iterable<? extends JavaFileObject> fileObjects = getJavaSourceFromString(nomClasse, laClasse);

        compiler.getTask(sortieErreur, fileManager, null, null, null, fileObjects).call();
        try {
            sortieErreur.close();
        } catch (IOException ex) {
            Logger.getLogger(KitJava.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!sortieErreur.toString().isEmpty()) {
            throw new Exception(sortieErreur.toString());
        }
    }

    /**
     * Fonction qui execute le code compile
     *
     * @throws Exception
     */
    public void executer(LesCases cases, int[] tab) throws Exception {
        ClassLoader cl = fileManager.getClassLoader(javax.tools.StandardLocation.CLASS_PATH);
        Class<?> classe = Class.forName("toucan.modele.algos.AlgoPerso", true, cl);
        Constructor<?> constructeur = classe.getConstructor(Class.forName("toucan.modele.cases.LesCases"), int[].class);
        Algo instance = (Algo) constructeur.newInstance(cases, tab);
        try {
            instance.trier();
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    /**
     * Fonction qui retourne une instance de KitJava
     *
     * @return instance;
     */
    public static KitJava getInstance() {
        return instance;
    }
}
