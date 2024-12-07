package DAOs;

import DAOs.Conectar;
import static DAOs.DAOGenerico.em;
import Entidades.paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOpaciente extends DAOGenerico<paciente> {

    private List<paciente> lista = new ArrayList<>();

    public DAOpaciente() {
        super(paciente.class);
    }

    public int autoCrmpaciente() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.cip) FROM paciente e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<paciente> listByNome(String nome_paciente) {
        return em.createQuery("SELECT e FROM paciente e WHERE e.cip) LIKE :nome_paciente").setParameter("nome_paciente", "%" + nome_paciente + "%").getResultList();
    }

    public List<paciente> listById(int cip) {
        return em.createQuery("SELECT e FROM paciente + e WHERE e.cip= :cip").setParameter("cip", cip).getResultList();
    }
    
      public List<paciente> listByIdade(String idade) {
        return em.createQuery("SELECT e FROM paciente e WHERE e.crm) LIKE: idade").setParameter("idade","%" + idade + "%").getResultList();
    }

    public List<paciente> listInOrderNome() {
        return em.createQuery("SELECT e FROM paciente e ORDER BY e.nome_paciente").getResultList();
    }

    public List<paciente> listInOrderId() {
        return em.createQuery("SELECT e FROM paciente e ORDER BY e.crm").getResultList();
    }
    
    public List<paciente> listInOrderArea() {
        return em.createQuery("SELECT e FROM paciente e ORDER BY e.area").getResultList();
    } 
        

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<paciente> lf;
        if (qualOrdem.equals("crm")) {
            lf = listInOrderId();
        } else {
           lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getcpi()+ "-" + lf.get(i).getnome_paciente()+ "-" + lf.get(i).getidade());
        }
        return ls;
    }
 public String[] listInOrderNomeStringsArray() {
        List<paciente> lf = listInOrderNome();
        String[] ls = new String[lf.size()];
        for (int i = 0; i < lf.size(); i++) {
            ls[i]=(lf.get(i).getcpi()+ "-" + lf.get(i).getnome_paciente());
        }
        return ls;
    }

    public boolean pacienteExiste(String cip) {
        String sql = "SELECT COUNT(*) FROM pacientes WHERE cip = ?";
        try (Connection conn = Conectar.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cip);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se encontrar pelo menos um registro
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Retorna false caso ocorra algum problema
    }

    public static void main(String[] args) {
        DAOpaciente daopaciente = new DAOpaciente();
        List<paciente> listapaciente = daopaciente.list();
        for (paciente paciente : listapaciente) {
            System.out.println(paciente.getcpi()+ "-" + paciente.getnome_paciente()+ "-" + paciente.getidade());
        }
    }
}

