CREATE TABLE tb_topico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao DATETIME NOT NULL,
    status VARCHAR(50),
    autor BIGINT,
    curso BIGINT,
    FOREIGN KEY (autor) REFERENCES tb_usuario(id),
    FOREIGN KEY (curso) REFERENCES tb_curso(id)
);
