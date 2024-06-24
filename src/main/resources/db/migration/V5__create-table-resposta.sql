CREATE TABLE tb_resposta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensagem TEXT NOT NULL,
    topico BIGINT,
    data_riacao DATETIME NOT NULL,
    autor BIGINT,
    solucao BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (topico) REFERENCES tb_topico(id),
    FOREIGN KEY (autor) REFERENCES tb_usuario(id)
);