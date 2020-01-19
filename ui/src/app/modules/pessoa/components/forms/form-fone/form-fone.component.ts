import { Component, OnInit, Input } from '@angular/core';
import { Telefone } from '../../../models/telefone';
import { Mask } from '../../../../shared/components/forms/mask/mask';

@Component({
  selector: 'app-pessoa-form-fone',
  templateUrl: './form-fone.component.html',
  styleUrls: ['./form-fone.component.css']
})
export class PessoaFormFoneComponent implements OnInit {

  @Input() readonly: boolean;
  @Input() telefones: Array<Telefone> = [];

  constructor() { }

  ngOnInit() {
  }

  public addFone() {
    const telefone = new Telefone();
    telefone.telefone = '';
    telefone.tipo = '';
    this.telefones.push(telefone);
  }

  public removeFone(index: number) {
    this.telefones.splice(index, 1);
  }


}
