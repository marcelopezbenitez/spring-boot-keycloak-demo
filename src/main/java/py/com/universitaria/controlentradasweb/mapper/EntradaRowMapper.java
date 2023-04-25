package py.com.universitaria.controlentradasweb.mapper;

import org.springframework.jdbc.core.RowMapper;
import py.com.universitaria.controlentradasweb.models.Entrada;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntradaRowMapper implements RowMapper<Entrada> {
    @Override
    public Entrada mapRow(ResultSet rs, int rowNum) throws SQLException {
        Entrada entrada = new Entrada();
        entrada.setNroSocio(rs.getString("NRO_SOCIO"));
        entrada.setNroCi(rs.getString("CEDULA_SOCIO"));
        entrada.setNombre(rs.getString("NOMBRE_SOCIO"));
        entrada.setFechaCompra(rs.getDate("FECHA_COMPRA").toLocalDate());
        entrada.setCatidadComprada(rs.getInt("CANTIDAD_COMPRADA"));
        entrada.setCantidadUsada(rs.getInt("CANTIDAD_USADA"));
        return entrada;
    }
}
