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
		maxCellSize: 20;
		minCellSize: 10;
		cellSpacing: 10;
		newRow: [ :r | 
			r
				add: #ingoing;
				add: #outgoing ];
		yourself
]

{ #category : #accessing }
MMCMooseAbstractQueryPresenter >> ingoing [
	^ ingoing
]

{ #category : #initialization }
MMCMooseAbstractQueryPresenter >> initializeWidgets [
	ingoing := self instantiate: RadioButtonPresenter.
	outgoing := self instantiate: RadioButtonPresenter.
	ingoing label: 'Ingoing'.
	outgoing label: 'Outgoing'.
	RadioButtonGroupPresenter new
		addRadioButton: ingoing;
		addRadioButton: outgoing;
		default: ingoing.
]

{ #category : #accessing }
MMCMooseAbstractQueryPresenter >> outgoing [
	^ outgoing
]
