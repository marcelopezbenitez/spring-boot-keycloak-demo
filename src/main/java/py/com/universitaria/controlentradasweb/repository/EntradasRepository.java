package py.com.universitaria.controlentradasweb.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import py.com.universitaria.controlentradasweb.mapper.EntradaRowMapper;
import py.com.universitaria.controlentradasweb.models.Entrada;

import javax.sql.DataSource;
import java.sql.Types;

@Repository
public class EntradasRepository extends JdbcDaoSupport {

    private static final String SQL_GET_ENTRADAS_POR_FILTRO =
            "SELECT * FROM CU.CUSEG_DATOS_ENTRADAS " +
                    "WHERE (NRO_SOCIO = ? OR ? IS NULL) " +
                    "AND (CEDULA_SOCIO = ? OR ? IS NULL)";
    public EntradasRepository(DataSource dataSource){
        setDataSource(dataSource);
    }

    public Entrada obtenerEntradaPorFiltro(Integer nroSocio, String nroCi) {
        try {
            Object[] args = {nroSocio, nroSocio, nroCi, nroCi};
            int[] types = {Types.BIGINT, Types.BIGINT, Types.VARCHAR, Types.VARCHAR};
            return getJdbcTemplate().queryForObject(SQL_GET_ENTRADAS_POR_FILTRO, args, types, new EntradaRowMapper());
        }catch (EmptyResultDataAccessException e){
            logger.info("No se encontraron resultados.");
            return null;
        }catch (DataAccessException e){
            logger.info("Ocurrio un error al obtener la entradas...");
            return null;
        }
    }

}
