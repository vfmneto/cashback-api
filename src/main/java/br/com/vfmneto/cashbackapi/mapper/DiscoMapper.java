package br.com.vfmneto.cashbackapi.mapper;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.dto.DiscoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscoMapper extends EntityMapper<DiscoDTO, Disco> {

    @Override
    Disco toEntity(DiscoDTO dto);

    @Override
    DiscoDTO toDto(Disco entity);

    default Disco fromId(Long id) {
        if (id == null) {
            return null;
        }
        Disco disco = new Disco();
        disco.setId(id);
        return disco;
    }
}
