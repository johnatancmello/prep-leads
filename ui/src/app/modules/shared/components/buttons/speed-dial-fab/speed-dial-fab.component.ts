import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { speedDialFabAnimations } from './speed-dial-fab.animation';

export interface FabButton {
  icon: string;
  tooltip: string;
}

export enum SpeedDialFabPosition {
  Top = 'top',
  Bottom = 'bottom',
  Left = 'left',
  Right = 'right'
}

@Component({
  selector: 'app-shared-speed-dial-fab',
  templateUrl: './speed-dial-fab.component.html',
  styleUrls: ['./speed-dial-fab.component.css'],
  animations: speedDialFabAnimations,
})
export class SharedSpeedDialFabComponent implements OnInit {

  @Input() reverseColumnDirection = false;
  @Input() fabButtons: FabButton[];
  @Output() fabClick = new EventEmitter();

  buttons = [];
  fabTogglerState = 'inactive';

  constructor() { }

  ngOnInit() { }

  private showItems() {
    this.fabTogglerState = 'active';
    this.buttons = this.fabButtons;
  }

  private hideItems() {
    this.fabTogglerState = 'inactive';
    this.buttons = [];
  }

  public onToggleFab() {
    this.buttons.length ? this.hideItems() : this.showItems();
  }

  public onClickFab(btn) {
    this.hideItems();
    this.fabClick.emit(btn);
  }


}
