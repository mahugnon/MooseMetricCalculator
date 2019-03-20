"
I'm the super class of invocation and navigation
"
Class {
	#name : #MMCMooseAbstractQueryPresenter,
	#superclass : #MMCAbstractPresenter,
	#instVars : [
		'ingoing',
		'outgoing'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCMooseAbstractQueryPresenter class >> defaultSpec [
	^ SpecTableLayout column
		maxCellSize: 100;
		minCellSize: 10;
		cellSpacing: #none;
		"vResizing: #shrinkWrap;
		hResizing: #shrinkWrap;"
			newRow: [ :r | 
			r
				add: #ingoing;
				add: #outgoing ]
			height: self toolbarHeight;
		yourself
]

{ #category : #accessing }
MMCMooseAbstractQueryPresenter >> ingoing [
	^ ingoing
]

{ #category : #initialization }
MMCMooseAbstractQueryPresenter >> initIngoing [
	ingoing := self instantiate: RadioButtonPresenter.
	ingoing label: 'Ingoing'.
	
]

{ #category : #initialization }
MMCMooseAbstractQueryPresenter >> initOutgoing [
	outgoing := self instantiate: RadioButtonPresenter.
	outgoing label: 'Outgoing'.
	
]

{ #category : #initialization }
MMCMooseAbstractQueryPresenter >> initializePresenter [
	ingoing activationAction: [ self queryDirection:#in ].
	outgoing activationAction: [ self queryDirection: #out ]
]

{ #category : #initialization }
MMCMooseAbstractQueryPresenter >> initializeWidgets [
	self initIngoing.
	self initOutgoing.
	RadioButtonGroupPresenter new
		addRadioButton: ingoing;
		addRadioButton: outgoing;
		default: ingoing
]

{ #category : #accessing }
MMCMooseAbstractQueryPresenter >> outgoing [
	^ outgoing
]

{ #category : #initialization }
MMCMooseAbstractQueryPresenter >> queryDirection: aSymbol [
	^ self getContext queryDirection: aSymbol
]
