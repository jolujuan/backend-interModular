package www.intermodular.com.appversion1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.intermodular.com.appversion1.model.dto.DadoMovimientoInfo;
import www.intermodular.com.appversion1.repository.DadoMovimientoRepository;
import www.intermodular.com.appversion1.service.DadoMovimientoService;
import www.intermodular.com.appversion1.service.mapper.DadoMovimientoMapper;


@Service
public class DadoMovimientoServiceImpl implements DadoMovimientoService{
    
    private final DadoMovimientoRepository dadoMovimientoRepository;

    @Autowired
    public DadoMovimientoServiceImpl(DadoMovimientoRepository dadoMovimientoRepository) {
        this.dadoMovimientoRepository = dadoMovimientoRepository;
    }

    @Override
    public DadoMovimientoInfo obtenerDadoPorId(Long id) {
        return DadoMovimientoMapper.INSTANCE.DadoMovimientoDbToDadoMovimientoInfo(dadoMovimientoRepository.findById(id).orElse(null));
    }

    @Override
    public int lanzarDadoMovimiento() {
       // Implementa la lógica para lanzar un dado de movimiento y obtener un número aleatorio entre 1 y 4
        // En este ejemplo, simplemente generamos un número aleatorio entre 1 y 4
        return (int) (Math.random() * 4) + 1;
    }
}
