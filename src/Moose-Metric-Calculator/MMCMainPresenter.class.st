"
I'm the main component of the application. I group all component together  to provide the desired functionnality
"
Class {
	#name : #MMCMainPresenter,
	#superclass : #MMCAbstractPresenter,
	#instVars : [
		'mooseModelPresenter',
		'mooseFormPresenter',
		'menuBar'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCMainPresenter class >> defaultSpec [
	^ SpecTableLayout column
		newRow: [ :r | r add: #menuBar ] height: self toolbarHeight;
		newRow: [ :r | 
			r
				add: #mooseModelPresenter;
				add: #mooseFormPresenter ];
		yourself
]

{ #category : #specs }
MMCMainPresenter class >> runMe [
	<example>
	self new openWithSpec
]

{ #category : #initialization }
MMCMainPresenter >> initMenuBar [
	menuBar := self instantiate: MMCMenuBarPresenter.
	
]

{ #category : #initialization }
MMCMainPresenter >> initMooseFormPresenter [
	mooseFormPresenter := self instantiate: MMCMooseFormPresenter.
]

{ #category : #initialization }
MMCMainPresenter >> initMooseModelPresenter [
	mooseModelPresenter := self instantiate: MMCMooseModelPresenter.
]

{ #category : #initialization }
MMCMainPresenter >> initialize [
	super initialize.
	self initializeContext.
]

{ #category : #initialization }
MMCMainPresenter >> initializeContext [
	self subscribeTo: MMCContext new.
]

{ #category : #initialization }
MMCMainPresenter >> initializeWidgets [
	self initMooseModelPresenter.
	self initMooseFormPresenter.
	self initMenuBar
]

{ #category : #accessing }
MMCMainPresenter >> menuBar [
	^ menuBar
]

{ #category : #accessing }
MMCMainPresenter >> mooseFormPresenter [
	^ mooseFormPresenter
]

{ #category : #accessing }
MMCMainPresenter >> mooseModelPresenter [
	^ mooseModelPresenter
]

{ #category : #initialization }
MMCMainPresenter >> subscribeTo: aContext [
	super subscribeTo: aContext.
	mooseModelPresenter subscribeTo: aContext.
	mooseFormPresenter subscribeTo: aContext.
	menuBar subscribeTo: aContext.
]

{ #category : #updating }
MMCMainPresenter >> update [
	
]
