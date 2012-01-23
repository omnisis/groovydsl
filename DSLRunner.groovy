class DSLRunner {

    static void loadDSL(script) {
    }
    
    static void usage() {
        println "usage: DSLRunner <scriptFile>\n"
        System.exit(1)
    }

    static void main(String [] args) {
        if(args.length < 1) { usage() }
        GroovyShell shell = new GroovyShell()
        shell.evaluate(new File(args[0]))
    }
}