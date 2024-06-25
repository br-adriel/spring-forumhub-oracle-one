CREATE TABLE tb_usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE tb_perfil_usuario (
    usuario_id BIGINT,
    perfil_id BIGINT,
    PRIMARY KEY (usuario_id, perfil_id),
    FOREIGN KEY (usuario_id) REFERENCES tb_usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (perfil_id) REFERENCES tb_perfil(id) ON DELETE CASCADE
);
