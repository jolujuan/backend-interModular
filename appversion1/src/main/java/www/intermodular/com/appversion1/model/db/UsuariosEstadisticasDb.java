    package www.intermodular.com.appversion1.model.db;


    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import www.intermodular.com.appversion1.security.entity.UsuarioDb;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "usuarios_estadisticas")
    public class UsuariosEstadisticasDb {

        @Id
        private Long idUsuario;
        private Long partidasTotales = 0L;
        private Long partidasPerdidas = 0L;
        private Long preguntasTotales = 0L;
        private Long preguntasAcertadas = 0L;
        private Long preguntasFalladas = 0L;

        @OneToOne
        private UsuarioDb usuario;
        
        
    }

