import { Component, OnInit, Input } from '@angular/core';
import { Pessoa } from '../../models/pessoa';
import { ImagemService } from '../../services/imagem.service';
import { ImagemFilter } from '../../models/imagem.filter';
import { Imagem } from 'src/app/modules/pessoa/models/imagem';

@Component({
  selector: 'app-pessoa-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class PessoaCardComponent implements OnInit {

  @Input() pessoa: Pessoa;
  @Input() imagemPadrao: string;

  constructor(private imagemService: ImagemService) { }

  ngOnInit() { }

  getBase64(): string {
    return Imagem.getImagemBase64(this.pessoa.imagem, this.imagemPadrao);
  }

}
