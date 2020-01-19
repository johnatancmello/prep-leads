import { Component, OnInit, Input } from '@angular/core';
import { Setor } from '../../../models/setor';
import { Item } from '../../../../shared/models/item';
import { SetorService } from '../../../services/setor.service';
import { SetorFilter } from '../../../models/setor.filter';
import { NavBarService } from 'src/app/modules/menu/services/nav-bar.service';

@Component({
  selector: 'app-setor-form-setor',
  templateUrl: './form-setor.component.html',
  styleUrls: ['./form-setor.component.css']
})
export class SetorFormSetorComponent implements OnInit {

  @Input() setor: Setor;
  @Input() readonly: boolean;
  itens = Array<Item>();

  constructor(
    private service: SetorService,
    private navBarService: NavBarService,
  ) { }

  ngOnInit() {
    this.getSetores();
  }

  private buildItens(setores: Array<Setor>) {
    this.itens = Array<Item>();
    setores.forEach(setor => {
      const item = new Item();
      item.value = setor.id;
      item.label = setor.nome;
      this.itens .push(item);
    });
  }

  private getSetores() {
    //this.navBarService.loading = true; //gera erro
    this.service.pesquisar(new SetorFilter().getParams())
      .then(response => {
        this.buildItens(response.content);
      });
     // .finally(() => { this.navBarService.loading = false; });
  }

}
