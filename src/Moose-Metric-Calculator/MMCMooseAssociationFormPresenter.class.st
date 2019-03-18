Class {
	#name : #MMCMooseAssociationFormPresenter,
	#superclass : #MMCMooseAbstractQueryPresenter,
	#instVars : [
		'label',
		'selectButton',
		'classesPresenter'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCMooseAssociationFormPresenter class >> defaultSpec [
	| spec |
	spec := super defaultSpec.
	^ spec
		cellSpacing: 0.5;
		maxCellSize: 20;
		newRow: [ :r | 
			r
				add: #label;
				add: #selectButton ];
		yourself
]

{ #category : #examples }
MMCMooseAssociationFormPresenter class >> runMe [
	<example>
	self new openWithSpec
]

{ #category : #accessing }
MMCMooseAssociationFormPresenter >> classesPresenter [
	^ classesPresenter
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> initClassesPresenter [
	classesPresenter := self newList
		sortingBlock: [ :a :b | a asString < b asString ]
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> initLabel [
	label := self newLabel
		emphasis: #bold;
		label: 'Classe'
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> initSelectButton [
	selectButton := self newButton label: '...'
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> initializePresenter [ 
selectButton action: [ self openClassesPresenter ]
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> initializeWidgets [
	super initializeWidgets.
	self initLabel.
	self initSelectButton.
	self initClassesPresenter
]

{ #category : #accessing }
MMCMooseAssociationFormPresenter >> label [
	^ label
]

{ #category : #accessing }
MMCMooseAssociationFormPresenter >> name [
	^ 'Association'
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> openClassesPresenter [
	classesPresenter openWithSpec
]

{ #category : #accessing }
MMCMooseAssociationFormPresenter >> selectButton [
	^ selectButton
]

{ #category : #updating }
MMCMooseAssociationFormPresenter >> update [
	classesPresenter items: context focus asOrderedCollection
]
