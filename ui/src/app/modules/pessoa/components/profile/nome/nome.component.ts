import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-pessoa-profile-nome',
  templateUrl: './nome.component.html',
  styleUrls: ['./nome.component.css']
})
export class PessoaProfileNomeComponent implements OnInit {

  @Input() pessoa; // Pode ser tanto funcionario quanto pessoa simples
  @Input() readonly: boolean;

  constructor() { }

  ngOnInit() {
  }

  public getNome(): string {
    return (this.pessoa && this.pessoa.nome) ? this.pessoa.nome : '';
  }

}
