package www.intermodular.com.appversion1.srv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import www.intermodular.com.appversion1.model.db.DadoMovimientoDb;
import www.intermodular.com.appversion1.model.dto.DadoMovimientoInfo;

@Mapper(uses = DadoMovimientoMapper.class)
public interface DadoMovimientoMapper {
    DadoMovimientoMapper INSTANCE= Mappers.getMapper(DadoMovimientoMapper.class);
    DadoMovimientoInfo DadoMovimientoDbToDadoMovimientoInfo(DadoMovimientoDb dadoMovimientoDb);

}
