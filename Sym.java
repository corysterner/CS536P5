import java.util.*;

/***
 * The Sym class defines a symbol-table entry. 
 * Each Sym contains a type (a Type).
 ***/
public class Sym { 
	private Type type;
	
	public Sym(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	public String toString() {
		return type.toString();
	}
}

/***
 * The FnSym class is a subclass of the Sym class just for functions.
 * The returnType field holds the return type and there are fields to hold
 * information about the parameters.
 ***/
class FnSym extends Sym {
    // new fields
    private Type returnType;
    private int numParams;
    private List<Type> paramTypes;
    
    public FnSym(Type type, int numparams) {
        super(new FnType());
        returnType = type;
        numParams = numparams;
    }

    public void addFormals(List<Type> L) {
        paramTypes = L;
    }
    
    public Type getReturnType() {
        return returnType;
    }

    public int getNumParams() {
        return numParams;
    }

    public List<Type> getParamTypes() {
        return paramTypes;
    }

    public String toString() {
        // make list of formals
        String str = "";
        boolean notfirst = false;
        for (Type type : paramTypes) {
            if (notfirst)
                str += ",";
            else
                notfirst = true;
            str += type.toString();
        }

        str += "->" + returnType.toString();
        return str;
    }
}

/***
 * The RecordSym class is a subclass of the Sym class just for variables 
 * declared to be a record type. 
 * Each RecordSym contains a symbol table to hold information about its 
 * fields.
 ***/
class RecordSym extends Sym {
    // new fields
    private IdNode recordType;  // name of the record type
    
    public RecordSym(IdNode id) {
        super(new RecordType(id));
        recordType = id;
    }

    public IdNode getRecordType() {
        return recordType;
    }    
}

/***
 * The RecordDefSym class is a subclass of the Sym class just for the 
 * definition of a record type. 
 * Each RecordDefSym contains a symbol table to hold information about its 
 * fields.
 ***/
class RecordDefSym extends Sym {
    // new fields
    private SymTab symTab;
    
    public RecordDefSym(SymTab table) {
        super(new RecordDefType());
        symTab = table;
    }

    public SymTab getSymTab() {
        return symTab;
    }
}
