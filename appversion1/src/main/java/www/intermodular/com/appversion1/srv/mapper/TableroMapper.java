package www.intermodular.com.appversion1.srv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import www.intermodular.com.appversion1.model.db.TableroDb;
import www.intermodular.com.appversion1.model.dto.TableroInfo;


@Mapper(uses = TableroMapper.class)
public interface TableroMapper {
    TableroMapper INSTANCE= Mappers.getMapper(TableroMapper.class);
    
    @Mapping(source="id",target="id_tablero")
    TableroInfo tableroDbToTableroInfo(TableroDb tableroDb);

}
