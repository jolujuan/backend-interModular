package www.intermodular.com.appversion1.srv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import www.intermodular.com.appversion1.model.db.JugadorDb;
import www.intermodular.com.appversion1.model.dto.JugadorInfo;

@Mapper(uses = JugadorMapper.class)
public interface JugadorMapper {
    JugadorMapper INSTANCE= Mappers.getMapper(JugadorMapper.class);
    @Mapping(source="id",target="id_jugador")
    JugadorInfo JugadoroDbToJugadorInfo(JugadorDb jugadorDb);
}
