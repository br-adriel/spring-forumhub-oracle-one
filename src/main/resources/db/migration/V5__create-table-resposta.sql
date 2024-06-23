CREATE TABLE tb_resposta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mensagem TEXT NOT NULL,
    topico INT,
    data_riacao DATETIME NOT NULL,
    autor INT,
    solucao BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (topico) REFERENCES tb_topico(id),
    FOREIGN KEY (autor) REFERENCES tb_usuario(id)
);