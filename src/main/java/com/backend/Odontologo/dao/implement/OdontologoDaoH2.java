package com.backend.Odontologo.dao.implement;
import com.backend.Odontologo.dbconnection.H2Connection;
import com.backend.Odontologo.entity.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.backend.Odontologo.dao.IDao;

public class OdontologoDaoH2 implements IDao<Odontologo>{

    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        String insert = "INSERT INTO ODONTOLOGOS(NOMBRE, APELLIDO, MATRICULA)VALUES(?,?,?)";
        Connection connection = null;
        Odontologo odontologoObtenido = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(insert,
                                                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setInt(3, odontologo.getMatricula());
            preparedStatement.execute();
            odontologoObtenido = new Odontologo(odontologo.getNombre(),
                                     odontologo.getApellido(), odontologo.getMatricula());
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                odontologoObtenido.setId(resultSet.getInt("id"));
            }
            connection.commit();
            LOGGER.info("El odontologo fue guardado" + odontologoObtenido);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Problema");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (Exception ex) {
                LOGGER.error("No se cerro la conexion" + ex.getMessage());
            }
        }
        return odontologoObtenido;
    }


        @Override
        public List<Odontologo> listarTodos() {
            Connection connection = null;
            List<Odontologo> odontologos = new ArrayList<>();
            try {
                connection = H2Connection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Odontologo odontologo = new Odontologo(resultSet.getString("NOMBRE"),
                            resultSet.getString("APELLIDO"),
                            resultSet.getInt("MATRICULA"));

                    odontologo.setId(resultSet.getInt("id"));

                    odontologos.add(odontologo);
                }

                LOGGER.info("Listado de todos los odontólogos: " + odontologos);

            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();

            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error("Ocurrió un error al intentar cerrar la Base de Datos. " + ex.getMessage());
                    ex.printStackTrace();
                }
            }

            return odontologos;
        }
}
