CREATE TABLE IF NOT EXISTS `permissoes` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `descricao` varchar(600) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id_usuario` varchar(50) NOT NULL,
  `senha` varchar(255) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `usuarios_permissoes` (
  `id_usuario` varchar(50) NOT NULL,
  `id_permissao` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_permissao`),
  KEY `id_permissao` (`id_permissao`),
  CONSTRAINT `usuarios_permissoes_id_permissoes_fk` FOREIGN KEY (`id_permissao`) REFERENCES `permissoes` (`id`),
  CONSTRAINT `usuarios_permissoes_id_usuarios_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `setores` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `metas` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `periodo` varchar(255) DEFAULT NULL,
  `descricao` varchar(600) DEFAULT NULL,
  `valor` FLOAT(7.4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `setores_metas` (
  `id_setor` bigint(20) unsigned NOT NULL,
  `id_meta` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id_setor`,`id_meta`),
  CONSTRAINT `setores_metas_id_setor_fk` FOREIGN KEY (`id_setor`) REFERENCES `setores` (`id`),
  CONSTRAINT `setores_metas_id_meta_fk` FOREIGN KEY (`id_meta`) REFERENCES `metas` (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `imagens` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `descricao` varchar(300) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `tamanho` bigint(10) DEFAULT NULL,
  `dados` longblob DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `pessoas` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_imagem` bigint(20) unsigned DEFAULT NULL,
  `id_responsavel` bigint(20) unsigned DEFAULT NULL,
  `status` varchar(255) DEFAULT 'ATIVO' NOT NULL,
  `nome` varchar(255) NOT NULL,
  `sexo` char(1) DEFAULT NULL,
  `idade` int(3) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `ocupacao` varchar(255) DEFAULT NULL,
  `data_do_cadastro` datetime NOT NULL,
  `local_do_cadastro` varchar(127) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_imagem_index` (`id_imagem`),
  KEY `id_responsavel_index` (`id_responsavel`),
  CONSTRAINT `pessoas_id_imagem_fk` FOREIGN KEY (`id_imagem`) REFERENCES `imagens` (`id`),
  CONSTRAINT `pessoas_id_responsavel_fk` FOREIGN KEY (`id_responsavel`) REFERENCES `pessoas` (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `telefones` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_pessoa` bigint(20) unsigned NOT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pessoa_index` (`id_pessoa`),
  CONSTRAINT `telefones_id_pessoa_fk` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoas` (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `funcionarios` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_pessoa` bigint(20) unsigned NOT NULL,
  `id_setor` bigint(20) unsigned NOT NULL,
  `id_usuario` varchar(50) NOT NULL,
  `carga_horaria` int(3) DEFAULT NULL,
  `status` varchar(255) DEFAULT 'ATIVO' NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pessoa_index` (`id_pessoa`),
  KEY `id_setor_index` (`id_setor`),
  KEY `id_usuario_index` (`id_usuario`),
  CONSTRAINT `funcionarios_id_pessoa_fk` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoas` (`id`),
  CONSTRAINT `funcionarios_id_setor_fk` FOREIGN KEY (`id_setor`) REFERENCES `setores` (`id`),
  CONSTRAINT `funcionarios_id_usuario_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `funcionarios_metas` (
  `id_funcionario` bigint(20) unsigned NOT NULL,
  `id_meta` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id_funcionario`,`id_meta`),
  KEY `id_funcionario_index` (`id_funcionario`),
  CONSTRAINT `funcionarios_metas_id_funcionario_fk` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionarios` (`id`),
  CONSTRAINT `funcionarios_metas_id_meta_fk` FOREIGN KEY (`id_meta`) REFERENCES `metas` (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `areas` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_area_dependente` bigint(20) unsigned NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_area_index` (`id_area_dependente`),
  CONSTRAINT `areas_id_area_dependente_fk` FOREIGN KEY (`id_area_dependente`) REFERENCES `areas` (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `formacoes` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_area` bigint(20) unsigned,
  `nome` varchar(255) NOT NULL,
  `carga_horaria` varchar(255) NULL,
  `status` varchar(255) DEFAULT 'ATIVO' NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_area_index` (`id_area`),
  CONSTRAINT `formacoes_id_area_fk` FOREIGN KEY (`id_area`) REFERENCES `areas` (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `modulos` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `carga_horaria` varchar(255) NULL,
  `status` varchar(255) DEFAULT 'ATIVO',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `formacoes_modulos` (
  `id_formacao` bigint(20) unsigned NOT NULL,
  `id_modulo` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id_formacao`,`id_modulo`),
  KEY `id_modulo_index` (`id_modulo`),
  KEY `id_formacao_index` (`id_formacao`),
  CONSTRAINT `formacoes_modulos_id_formacao_fk` FOREIGN KEY (`id_formacao`) REFERENCES `formacoes` (`id`),
  CONSTRAINT `formacoes_modulos_id_modulo_fk` FOREIGN KEY (`id_modulo`) REFERENCES `modulos` (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `interesses` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_pessoa` bigint(20) unsigned NOT NULL,
  `id_funcionario` bigint(20) unsigned NOT NULL,
  `status` varchar(255) NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `data_do_cadastro` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pessoa_index` (`id_pessoa`),
  KEY `id_funcionario_index` (`id_funcionario`),
  CONSTRAINT `interesses_id_pessoa_fk` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoas` (`id`),
  CONSTRAINT `interesses_id_funcionario_fk` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionarios` (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `interesses_modulos` (
  `id_interesse` bigint(20) unsigned NOT NULL,
  `id_modulo` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id_interesse`,`id_modulo`),
  KEY `id_interesse_index` (`id_interesse`),
  KEY `id_modulo_index` (`id_modulo`),
  CONSTRAINT `interesses_modulos_id_interesse_fk` FOREIGN KEY (`id_interesse`) REFERENCES `interesses` (`id`),
  CONSTRAINT `interesses_modulos_id_modulo_fk` FOREIGN KEY (`id_modulo`) REFERENCES `modulos` (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `contatos`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_pessoa` bigint(20) unsigned NOT NULL,
  `id_funcionario` bigint(20) unsigned NOT NULL,
  `id_interesse` bigint(20) unsigned NOT NULL,
  `id_visita` bigint(20) unsigned NULL,
  `status` varchar(255) NOT NULL,
  `observacao` varchar(600) NOT NULL,
  `data_do_contato` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pessoa_index` (`id_pessoa`),
  KEY `id_funcionario_index` (`id_funcionario`),
  KEY `id_interesse_index` (`id_interesse`),
  KEY `id_visita_index` (`id_visita`),
  CONSTRAINT `contatos_id_interesse_fk` FOREIGN KEY (`id_interesse`) REFERENCES `interesses` (`id`),
  CONSTRAINT `contatos_id_pessoa_fk` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoas` (`id`),
  CONSTRAINT `contatos_id_funcionario_fk` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionarios` (`id`)
)ENGINE=InnoDB CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `visitas` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_pessoa` bigint(20) unsigned NOT NULL,
  `id_funcionario` bigint(20) unsigned NOT NULL,
  `id_contato` bigint(20) unsigned NULL,
  `status` varchar(255) NOT NULL,
  `observacao` varchar(600) DEFAULT NULL,
  `data_do_agendamento` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pessoa_index` (`id_pessoa`),
  KEY `id_funcionario_index` (`id_funcionario`),
  KEY `id_contato_index` (`id_contato`),
  CONSTRAINT `visitas_id_pessoa_fk` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoas` (`id`),
  CONSTRAINT `visitas_id_contato_fk` FOREIGN KEY (`id_contato`) REFERENCES `contatos` (`id`),
  CONSTRAINT `visitas_id_funcionario_fk` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionarios` (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

ALTER TABLE contatos ADD CONSTRAINT contatos_id_visita_fk
FOREIGN KEY(id_visita) REFERENCES visitas (id);

CREATE TABLE IF NOT EXISTS `alunos` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_pessoa` bigint(20) unsigned NOT NULL,
  `id_funcionario` bigint(20) unsigned NOT NULL,
  `status` varchar(255) DEFAULT 'ATIVO' NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pessoa_index` (`id_pessoa`),
  KEY `id_funcionario_index` (`id_funcionario`),
  CONSTRAINT `alunos_id_pessoa_fk` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoas` (`id`),
  CONSTRAINT `alunos_id_funcionario_fk` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionarios` (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;