INSERT INTO `pessoas` (`id`, `nome`, `data_do_cadastro`, `local_do_cadastro`) VALUES ('1', 'Admin', '2019-06-07 01:52:06', 'SYSTEM');
INSERT INTO `usuarios` (`id_usuario`, `senha`) VALUES ('admin', '$2a$10$yXGUN/UD3comeDn3ei7b.e3LBjxXEbMJ9y15N524Hy1vCbeOf/Dfe');
INSERT INTO `setores` (`id`, `nome`) VALUES ('1', 'si');
INSERT INTO `funcionarios` (`id_pessoa`, `id_setor`, `id_usuario`, `carga_horaria`, `status`) VALUES ('1', '1', 'admin', 44, 'ATIVO');
INSERT INTO `metas` (`id`, `nome`, `periodo`, `descricao`, `valor`) VALUES ('1', 'cadastros', '30', 'cadastros', '1500');
INSERT INTO `setores_metas` (`id_setor`, `id_meta`) VALUES ('1', '1');

INSERT INTO `permissoes` (`id`, `nome`, `descricao`) VALUES ('1', 'CONSULTAR', 'Pode consultar informacaoes');
INSERT INTO `usuarios_permissoes` (`id_usuario`, `id_permissao`) VALUES ('admin', '1');
INSERT INTO `funcionarios_metas` (`id_funcionario`, `id_meta`) VALUES ('1', '1');
