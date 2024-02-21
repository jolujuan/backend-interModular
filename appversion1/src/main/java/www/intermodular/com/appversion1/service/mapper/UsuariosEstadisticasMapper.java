package www.intermodular.com.appversion1.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuariosEstadisticasMapper {
    UsuariosEstadisticasMapper INSTANCE=Mappers.getMapper(UsuariosEstadisticasMapper.class);

}