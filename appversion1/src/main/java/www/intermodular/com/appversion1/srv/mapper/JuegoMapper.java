package www.intermodular.com.appversion1.srv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = JuegoMapper.class)
public interface JuegoMapper {
    JuegoMapper INSTANCE= Mappers.getMapper(JuegoMapper.class);

}
