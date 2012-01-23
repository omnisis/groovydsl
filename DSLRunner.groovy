class DSLRunner {

  def charTypes = []
  
  /*
   * This class will hold details about each charactertype
   */
  static class CharacterType { 
    def _name
    def _description
    def _disposition
    def _bonuses = [:]
    def _baseAttrs = [:]
    
    CharacterType(name) { 
      _name = name
    }
    
    def disposition(disp) { 
      _disposition = disp
    }
    def description(desc) { 
      _description = desc
    }
    def baseAttrs(Map m) { 
      _baseAttrs = m
    }
    def bonuses(Map m) { 
      _bonuses = m
    }
    
    String toString() { 
      "\n${_name}:${_disposition} (${_description})\n\t${_baseAttrs}\n\t${_bonuses}"
    }
    
  };



  static enum DISPTYPE { 
    GOOD,
    EVIL,
    NEUTRAL
  }

  void loadDSL(Closure cl) {
    println "loading DSL ..."
    cl.delegate = this
    cl()
  }

  // intercept missing methods, if they look like
  //     methodName { ... }
  // treat them like a new character type definition
  def methodMissing(String name, args) {
    println "methodMissing: ${name}"
    if(args.length == 1 && args[0] instanceof Closure) { 
      println "encountered new character archetype: ${name}"    
      newCharacterType(name, args[0])
    }
  }

  def newCharacterType(name, cl) { 
    CharacterType cType = new CharacterType(name)
    cl.delegate = cType
    cl()
    this.charTypes += cType
  }
  
  void usage() {
    println "usage: DSLRunner <scriptFile>\n"
    System.exit(1)
  }
  
  static void main(String [] args) {
    DSLRunner runner = new DSLRunner()
    if(args.length < 1) { runner.usage() }
    
    def script = new File(args[0]).text
    def dsl = """
	  run {
	    ${script}
	  }
	"""
    
    def binding = new Binding()
    binding.run = { Closure cl -> runner.loadDSL(cl) }	 
    binding.GOOD = DISPTYPE.GOOD
    binding.EVIL = DISPTYPE.EVIL
    binding.NEUTRAL = DISPTYPE.NEUTRAL
    GroovyShell shell = new GroovyShell(binding)
    shell.evaluate(dsl)

    // print out character types
    runner.charTypes.each {  println it }
  }
}