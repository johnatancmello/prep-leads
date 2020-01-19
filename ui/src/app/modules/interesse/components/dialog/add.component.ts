import { Component,
  OnInit,
  ChangeDetectorRef,
  Inject
} from '@angular/core';

import { MatDialog,
  MatBottomSheetRef,
  MAT_BOTTOM_SHEET_DATA,
  MatBottomSheet,
  MatSelectionList
} from '@angular/material';

import { Modulo } from '../../../curso/models/modulo';
import { InteresseFilter } from '../../models/interesse.filter';
import { InteresseService } from '../../services/interesse.service';
import { SharedHandlerMensageService } from '../../../shared/services/error/handler-mensage.service';
import { NavBarService } from '../../../menu/services/nav-bar.service';
import { SegurancaLoggedService } from '../../../seguranca/services/logged.service';
import { Interesse } from '../../models/interesse';
import { ContatoDialogComponent } from '../../../contato/components/dialog/dialog.component';
import { EventEmitterService } from '../../../shared/services/broadcaster/event-emitter';
import { Funcionario } from 'src/app/modules/funcionario/models/funcionarios';
import { Events } from 'src/app/modules/shared/services/broadcaster/events';
import { CursoPainelFormacaoComponent } from 'src/app/modules/curso/components/painel-formacao/painel-formacao.component';
import { Item } from '../../../shared/models/item';
import { InteresseTipos } from '../../models/interesse.status';

@Component({
  selector: 'app-pessoa-interesse-dialog-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class InteresseDialogAddComponent implements OnInit {


  public modulosPersonalizados: Array<Modulo>;
  private tipos = [];
  private readonly: boolean;
  private filter = new InteresseFilter();
  private interesse = new Interesse();

  constructor(
    private interesseService: InteresseService,
    private handlerMensage: SharedHandlerMensageService,
    private navBarService: NavBarService,
    private logged: SegurancaLoggedService,
    private changeDetectorRef: ChangeDetectorRef,
    private dialog: MatDialog,
    private bottomSheet: MatBottomSheet,
    private bottomSheetRef: MatBottomSheetRef<InteresseDialogAddComponent>,
    @Inject(MAT_BOTTOM_SHEET_DATA) public data: any
  ) {
    if (data.interesse) {
      this.pesquisar(data.interesse.id);
      this.readonly = true;
    } else {
      this.interesse = Interesse.newInteresse();
      this.readonly = false;
    }
  }

  ngOnInit() {
    this.buildTipos();
  }

  private buildTipos() {
    for (const index in InteresseTipos) {
      if (typeof InteresseTipos[index] === 'string') {
        const item = new Item();
        item.label = InteresseTipos[index];
        item.value = InteresseTipos[index];
        this.tipos.push(item);
      }
    }
  }


  public getTextAlterarSalvar(): string {
    return this.readonly ? 'edit' : 'done' ;
  }

  public hasModulos(): boolean {
    return this.interesse.modulos && this.interesse.modulos.length !== 0;
  }

  public hasTipo(): boolean {
    return (this.interesse.tipo) ? true : false;
  }

  public openDialogContato() {
    const interesse = this.interesse;
    const bottomSheetRef = this.bottomSheet.open(
      ContatoDialogComponent,
      { data: { interesse } }
    );

    bottomSheetRef.afterDismissed().subscribe((result) => {
      // if (result) { this.addOrChange(result); }
    });
  }

  public openDialogFormacoes(event) {
    // document.documentElement.requestFullscreen();

    const dialogRef = this.dialog.open(
      CursoPainelFormacaoComponent, CursoPainelFormacaoComponent.config()
    );

    dialogRef.afterClosed().subscribe(formacao => {
      if (formacao) { this.addFormacao(formacao); }
      // document.exitFullscreen();
    });
  }

  public close() {
    this.bottomSheetRef.dismiss();
  }

  public aterarSavar() {
    if (this.readonly) {
      this.readonly = false;
    } else {
      this.buildInteresse();
    }
  }

  public limpar(event) {
    this.interesse.modulos = new Array<Modulo>();
  }

  public pesquisar(id: number) {
    // this.navBarService.loading = true;  //gera erro
    this.interesseService.pesquisarById(id)
      .then(response => {
        this.interesse = response;
        this.filter.totalRecords = response.totalElements;
        this.changeDetectorRef.detectChanges();
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      });
    // .finally(() => { this.navBarService.loading = false; });
  }

  public onNgModelChange(event: MatSelectionList) {
    this.modulosPersonalizados = event
      .selectedOptions.selected.map((selectedOption) => {
      return selectedOption.value;
      }
    );
  }

  private buildInteresse() {
    this.interesse.modulos = this.modulosPersonalizados;
    this.addOrChange();
  }

  private addFormacao(formacao) {
    formacao.modulos.forEach(modulo => {
      this.interesse.modulos.push(modulo);
    });
    this.changeDetectorRef.detectChanges();
  }

  private addOrChange() {
    const interesse = this.getInteresseClone(this.interesse);
    if (interesse.id) {
      this.change(interesse);
    } else {
      this.add(interesse);
    }
  }

  private change(interesse: Interesse) {
    this.navBarService.loading = true;
    this.addPessoa(interesse);
    this.cleanAdditionalInformationOfModulos(interesse);
    this.interesseService.alterar(interesse)
      .then(response => {
        EventEmitterService.get(Events.interessesChange).emit();
        this.handlerMensage.success(response);
        this.bottomSheetRef.dismiss();
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  private add(interesse: Interesse) {
    this.navBarService.loading = true;
    this.addFuncionarioLogado(interesse);
    this.addPessoa(interesse);
    this.cleanAdditionalInformationOfModulos(interesse);
    this.interesseService.adicionar(interesse)
      .then(response => {
        EventEmitterService.get(Events.interessesChange).emit();
        this.handlerMensage.success(response);
        this.bottomSheetRef.dismiss();
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  private addFuncionarioLogado(interesse: Interesse) {
    interesse.funcionario = new Funcionario();
    interesse.funcionario.id = this.logged.getIdFuncionario();
  }

  private addPessoa(interesse: Interesse) {
    interesse.pessoa = this.data.pessoa;
  }

  private cleanAdditionalInformationOfModulos(interesse: Interesse) {
    const modulos = new Array<Modulo>();
    interesse.modulos.forEach((modulo) => {
      const moduloClone = new Modulo();
      moduloClone.id = modulo.id;
      modulos.push(moduloClone);
    });
    interesse.modulos = modulos;
  }

  private getInteresseClone(interesse: Interesse): Interesse {
    return JSON.parse(JSON.stringify(interesse));
  }

}
