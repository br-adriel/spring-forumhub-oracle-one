CREATE TABLE tb_topico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao DATETIME NOT NULL,
    status VARCHAR(50),
    autor INT,
    curso INT,
    FOREIGN KEY (autor) REFERENCES tb_usuario(id),
    FOREIGN KEY (curso) REFERENCES tb_curso(id)
);
