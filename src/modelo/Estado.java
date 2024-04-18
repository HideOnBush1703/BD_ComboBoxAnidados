
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class Estado {
    
    private int idEstado;
    private String nombreEstado;

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
    
    public String toString(){
        return this.nombreEstado;
    }
    
    public Vector<Estado> mostrarEstados(Integer idPais){
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        Vector<Estado> vectorEstados = new Vector<Estado>(); //creando un vector de paises
        Estado estado = null; //creando un objeto de pais
        
        try{
            
            Conexion con=new Conexion();
            Connection conexion = con.getConnection();
            
            ps=conexion.prepareStatement("select * from estados where idPais="+idPais);
            rs=ps.executeQuery();
            
            estado=new Estado();//instanciando el objeto
            estado.setIdEstado(0); //id del pais es 0
            estado.setNombreEstado("Seleccione estado"); //nombre del pais es seleccione pais y como tiene id 0 no afecta
            vectorEstados.add(estado);
            
            while(rs.next()){
                //agregando cada pais que encontremos en nuestra base de datos está siendo agregado al vector
                estado=new Estado();
                estado.setIdEstado(rs.getInt("idestado")); 
                estado.setNombreEstado(rs.getString("nombreEstado")); 
                vectorEstados.add(estado);
                
            }
            
            rs.close();
            
        }catch(Exception ex){
            System.err.println("Error, "+ex);
        }
        
        return vectorEstados;
    }
    
}
