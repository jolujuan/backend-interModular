package www.intermodular.com.appversion1.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import www.intermodular.com.appversion1.model.db.TableroDb;
import www.intermodular.com.appversion1.model.dto.TableroInfo;
import www.intermodular.com.appversion1.model.dto.TableroList;


@Mapper()
public interface TableroMapper {
    TableroMapper INSTANCE= Mappers.getMapper(TableroMapper.class);
    

    TableroInfo tableroDbToTableroInfo(TableroDb tableroDb);
  //  TableroList tableroDbToTableroList(TableroDb tableroDb);
    List<TableroList> tableroDbToTableroList(List<TableroDb> tablerodb);
}
