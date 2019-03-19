"
I'm the container of moose model understudy
"
Class {
	#name : #MMCMooseModelPresenter,
	#superclass : #MMCAbstractPresenter,
	#instVars : [
		'mooseModels'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCMooseModelPresenter class >> defaultSpec [
	^ SpecLayout composed
		add: #mooseModels;
		yourself
]

{ #category : #examples }
MMCMooseModelPresenter class >> runMe [
	<example>
	self new openWithSpec
]

{ #category : #initialization }
MMCMooseModelPresenter >> initiMooseModels [
	mooseModels := self instantiate: FastTablePresenter.
	mooseModels
		displayBlock: [ :item | 
			item class = MooseModel
				ifTrue: [ item name ]
				ifFalse: [ item asString copyReplaceAll: 'famixt' with: 'famix' ] ];
		icons: [ :item | item mooseIcon ];
		sortingBlock: [ :a :b | a asString < b asString ]
]

{ #category : #initialization }
MMCMooseModelPresenter >> initializePresenter [
	mooseModels
		whenSelectedItemChanged: [ :item | item ifNotEmpty: (self setFocus: item) ]
]

{ #category : #initialization }
MMCMooseModelPresenter >> initializeWidgets [
	self initiMooseModels
]

{ #category : #accessing }
MMCMooseModelPresenter >> mooseModels [
	^ mooseModels
]

{ #category : #initialization }
MMCMooseModelPresenter >> setFocus: item [
	^ [ context focus:item]
]

{ #category : #subscription }
MMCMooseModelPresenter >> subscribeTo: aContext [
	super subscribeTo: aContext.
	self update
]

{ #category : #subscription }
MMCMooseModelPresenter >> update [
	^ mooseModels items: context mooseModel
]
