Class {
	#name : #MMCMenuBarPresenter,
	#superclass : #MVSMenuPresenter,
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #initialization }
MMCMenuBarPresenter >> getNavButtons [
	^ menu menuGroups first
]

{ #category : #initialization }
MMCMenuBarPresenter >> initialize [
	super initialize.
	mvsHistory := MMCHistory new
]

{ #category : #initialization }
MMCMenuBarPresenter >> menuBarFor: aPresenter [
	| aMenu |
	aMenu := MenuPresenter new
		addGroup: [ :group | 
			group
				addItem: [ :item | 
					item
						name: nil;
						description: 'Previous';
						icon: (self iconNamed: #smallUndoIcon);
						action: [ self prev ] ].
			group
				addItem: [ :item | 
					item
						name: nil;
						description: 'Next';
						icon: (self iconNamed: #smallRedoIcon);
						action: [ self next ] ] ].
	aMenu applyTo: aPresenter.
	^ aMenu
]
