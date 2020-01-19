import { Component, OnInit, Input } from '@angular/core';
import { Usuario } from '../../../models/usuario';

@Component({
  selector: 'app-funcionario-form-usuario',
  templateUrl: './form-usuario.component.html',
  styleUrls: ['./form-usuario.component.css']
})
export class FuncionarioFormUsuarioComponent implements OnInit {

  @Input() readonly: boolean;
  @Input() usuario: Usuario;

  constructor() { }

  ngOnInit() {
  }

}
